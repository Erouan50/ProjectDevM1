import pika

connection = pika.BlockingConnection(pika.ConnectionParameters(
        host='localhost'))
channel = connection.channel()

channel.basic_publish(exchange='test',
                      routing_key='test',
                      body='Hello World!')
print " [x] Sent 'Hello World!'"
connection.close()
