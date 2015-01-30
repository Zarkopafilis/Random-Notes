import socket 
import threading
import subprocess
import atexit

print "ControllerThing started"

def close_sock(socket):
    socket.close()

server = socket.socket(socket.AF_INET , socket.SOCK_STREAM)
server.bind(("0.0.0.0" , 1337))
server.listen(5)
atexit.register(close_sock, server);

def handle_client(client_socket):
    request = client_socket.recv(1024)
    print request
    request = request.split('-')
    username = request[0]
    password = request[1]
    command = ''
    for x in range(len(request) - 2):
        command += request[x + 2] + '-'
    command = command[0:-1]    
    print username
    print password
    print command
    
    if(username == 'something' and password == 'something'):
        subprocess.call(command , shell=True)

    client_socket.send('ok')
    client_socket.close()
	
while True:
    client,addr = server.accept();
    client_handler = threading.Thread(target=handle_client , args=(client,))
    client_handler.start()

