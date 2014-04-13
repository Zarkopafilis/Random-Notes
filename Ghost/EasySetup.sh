#!/bin/bash

sudo apt-get update
sudo apt-get upgrade
sudo aptitude install build-essential zip
wget http://nodejs.org/dist/latest/node-v0.10.18.tar.gz
tar -zxvf node-v0.10.18.tar.gz
cd node-v0.10.18
./configure
make
sudo make install
sudo mkdir -p /var/www
cd /var/www
sudo wget {url to ghost download}
sudo unzip ghost-{version}.zip
sudo npm install

#######

FILE="config.js"

/bin/cat <<EOM >$FILE

 url: 'http://mysite.com',
 server: {
     host: 'mysite.com',
     port: '80'
 }
 
EOM

#######

cd /etc/init

FILE="ghost.conf"

/bin/cat <<EOM >$FILE

#/etc/init/ghost.conf
description "Ghost Blog"
author "Your Name"
# Start the service after everything loaded
start on (local-filesystems and net-device-up IFACE=eth0)
stop on shutdown
# Automatically restart service
respawn
respawn limit 99 5
script
    # Navigate to your app directory
    cd /var/www

    # Run the script with Node.js and output to a log
    export NODE_ENV=production
    exec /usr/local/bin/npm start /var/www 2>&1 >> /var/log/ghost.log
end script

EOM

#######

sudo service ghost start
