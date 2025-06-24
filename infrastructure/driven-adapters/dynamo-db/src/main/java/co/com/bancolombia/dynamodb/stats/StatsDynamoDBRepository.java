package co.com.bancolombia.dynamodb.stats;

import co.com.bancolombia.dynamodb.stats.mapper.StatsEntityMapper;
import co.com.bancolombia.model.stats.Stats;
import co.com.bancolombia.model.stats.gateways.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;


@RequiredArgsConstructor
@Component
public class StatsDynamoDBRepository implements StatsRepository {

        private final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;
        private final StatsEntityMapper statsEntityMapper;

        @Override
        public Mono<Stats> save(Stats stats) {
                StatsEntity entity = statsEntityMapper.toEntity(stats);

                // 🔧 Nombre corregido: debe coincidir exactamente con el nombre de la tabla en DynamoDB local
                DynamoDbAsyncTable<StatsEntity> table = dynamoDbEnhancedAsyncClient
                        .table("stats", TableSchema.fromBean(StatsEntity.class));

                return Mono.fromFuture(table.putItem(entity))
                        .thenReturn(stats);
        }
}
