from kafka import KafkaProducer
import json
import csv

# Kafka producer configuration
producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8'),
    key_serializer=lambda v: str(v).encode('utf-8') if v else None  # Convert key to bytes
)

# Kafka topic to produce messages to
topic = 'sensor'

# Function to produce a message to the Kafka topic
def produce_message(id, value, timestamp):
    data = {
        'id': id,
        'value': value,
        'timestamp': timestamp
    }
    producer.send(topic, key=str(id), value=data)

# Read data from CSV file and produce messages
csv_file = 'myFile.csv'

with open(csv_file, 'r') as file:
    csv_reader = csv.reader(file)
    header = next(csv_reader)  # Assuming the first row is a header

    for row in csv_reader:
        # Assuming the CSV file columns are: id, value, timestamp
        id = int(row[0])
        value = float(row[1])
        timestamp = row[2]

        produce_message(id, value, timestamp)

# Close Kafka producer
producer.close()
