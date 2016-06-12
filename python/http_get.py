#!/usr/bin/python
import httplib
import urllib

params = urllib.urlencode({'@LOOKUPADDRESS':'12.9.138.11'})
headers = {"Content-type":"application/x-www-form-urlencoded","Accept":"text/plain","User-Agent":"synico@gmail.com"}
#headers1 = {'User-Agent':'synico@gmail.com'}

conn = httplib.HTTPConnection('bot.whatismyipaddress.com')
conn.debuglevel = 1
conn.request('POST', '/', params, headers1)
r1 = conn.getresponse()
#print r1.status, r1.reason

data1 = r1.read()
print data1
conn.close()

#conn1 = httplib.HTTPConnection('bot.whatismyipaddress.com')
#conn1.debuglevel = 1
#conn1.request("GET","/ip/12.9.138.11")
#r2 = conn1.getresponse()
#data2 = r2.read()
#print data2
