package co.com.bancolombia.dynamodb.stats;


import co.com.bancolombia.dynamodb.stats.mapper.StatsEntityMapper;
import co.com.bancolombia.model.stats.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StatsDynamoDBRepositoryTest {

        private DynamoDbEnhancedAsyncClient enhancedClient;
        private DynamoDbAsyncTable<StatsEntity> mockTable;
        private StatsEntityMapper mapper;
        private StatsDynamoDBRepository repository;

        @BeforeEach
        void setup() {
                enhancedClient = mock(DynamoDbEnhancedAsyncClient.class);
                mapper = mock(StatsEntityMapper.class);
                mockTable = mock(DynamoDbAsyncTable.class);

                when(enhancedClient.table(eq("stats"), any(TableSchema.class)))
                        .thenReturn(mockTable);

                repository = new StatsDynamoDBRepository(enhancedClient, mapper);
        }

        @Test
        void shouldSaveStatsSuccessfully() {
                Stats stats = Stats.builder()
                        .timestamp("2025-06-23T18:00:00")
                        .totalContactoClientes(100)
                        .build();

                StatsEntity entity = new StatsEntity();
                entity.setTimestamp("2025-06-23T18:00:00");
                entity.setTotalContactoClientes(100);

                when(mapper.toEntity(stats)).thenReturn(entity);
                when(mockTable.putItem(entity)).thenReturn(CompletableFuture.completedFuture(null));

                StepVerifier.create(repository.save(stats))
                        .expectNext(stats)
                        .verifyComplete();

                verify(mockTable).putItem(entity);
        }

        @Test
        void shouldReturnErrorWhenDynamoFails() {
                Stats stats = Stats.builder()
                        .timestamp("2025-06-23T18:00:00")
                        .totalContactoClientes(100)
                        .build();

                StatsEntity entity = new StatsEntity();
                entity.setTimestamp("2025-06-23T18:00:00");

                when(mapper.toEntity(stats)).thenReturn(entity);
                when(mockTable.putItem(entity)).thenReturn(
                        CompletableFuture.failedFuture(new RuntimeException("Dynamo error"))
                );

                StepVerifier.create(repository.save(stats))
                        .expectErrorMatches(err -> err instanceof RuntimeException &&
                                err.getMessage().equals("Dynamo error"))
                        .verify();
        }
}
