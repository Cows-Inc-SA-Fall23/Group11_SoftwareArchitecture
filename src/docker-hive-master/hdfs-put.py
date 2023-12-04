from kafka import KafkaConsumer
from hdfs import InsecureClient
import json

# Kafka Consumer Configuration
bootstrap_servers = 'localhost:9092'
topic = 'sensor'
group_id = 'my_consumer_group'

# HDFS Configuration
hdfs_url = 'http://namenode:8020'
hdfs_path = '/user/hive/csv'

client = InsecureClient(hdfs_url)

def consume_from_kafka_and_write_to_hdfs():
    consumer = KafkaConsumer(topic, group_id=group_id, bootstrap_servers=bootstrap_servers, auto_offset_reset='earliest')

    try:
        for msg in consumer:
            data = json.loads(msg.value.decode('utf-8'))
            
            print(f"Processing message: {data}")

            sensor_id = data['sensor_id']
            value = data['value']
            timestamp = data['timestamp']

            # Write to HDFS
            hdfs_file_path = f'{hdfs_path}/data_{sensor_id}.json'
            print(f"Writing to HDFS file: {hdfs_file_path}")

            with client.write(hdfs_file_path, overwrite=True) as writer:
                writer.write(json.dumps(data))


    finally:
        consumer.close()

if __name__ == '__main__':
    consume_from_kafka_and_write_to_hdfs()
