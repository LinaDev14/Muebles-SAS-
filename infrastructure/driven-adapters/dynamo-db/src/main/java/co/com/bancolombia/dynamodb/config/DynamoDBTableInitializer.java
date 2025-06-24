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
                                System.out.println("🛠️ Tabla 'stats' no existe. Creando...");

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

                                dynamoDbAsyncClient.createTable(request).thenAccept(resp ->
                                        System.out.println(" Tabla 'stats' creada correctamente")
                                ).exceptionally(ex -> {
                                        System.err.println(" Error creando la tabla: " + ex.getMessage());
                                        return null;
                                });
                        } else {
                                System.out.println("✔️ La tabla 'stats' ya existe.");
                        }
                }).exceptionally(ex -> {
                        System.err.println(" Error verificando tablas: " + ex.getMessage());
                        return null;
                });
        }
}
