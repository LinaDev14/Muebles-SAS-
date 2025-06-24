package co.com.bancolombia.dynamodb.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Component
@Profile("local")
@RequiredArgsConstructor
public class DynamoDBTableInitializer {

        private final DynamoDbAsyncClient dynamoDbAsyncClient;

        @PostConstruct
        public void createTableIfNotExists() {
                String tableName = "stats";

                dynamoDbAsyncClient.listTables().thenAccept(result -> {
                        if (!result.tableNames().contains(tableName)) {
                                CreateTableRequest request = CreateTableRequest.builder()
                                        .tableName(tableName)
                                        .keySchema(KeySchemaElement.builder()
                                                .attributeName("timestamp")
                                                .keyType(KeyType.HASH)
                                                .build())
                                        .attributeDefinitions(AttributeDefinition.builder()
                                                .attributeName("timestamp")
                                                .attributeType(ScalarAttributeType.S)
                                                .build())
                                        .provisionedThroughput(ProvisionedThroughput.builder()
                                                .readCapacityUnits(5L)
                                                .writeCapacityUnits(5L)
                                                .build())
                                        .build();

                                dynamoDbAsyncClient.createTable(request).exceptionally(ex -> null);
                        }
                }).exceptionally(ex -> null);
        }
}