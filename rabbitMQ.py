import pika
import json

connection = pika.BlockingConnection(pika.ConnectionParameters(
        host='localhost'))
channel = connection.channel()

message = json.dumps({'id':10,'status':0,'tableId':1,'creationDate':'2012-07-06','menus':[{'id':100,'name':"Omelette Du Jour",'description':"ask your server for our daily selection with pommes frites and organic greens."},{'id':101,'name':"Parisian Omelette",'description':"french ham and brie omelette with pommes frites and greens"}]})

channel.basic_publish(exchange='test',
                      routing_key='test',
                      body=message)
print message
connection.close()
