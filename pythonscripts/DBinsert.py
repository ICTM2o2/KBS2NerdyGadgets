import psutil #pip install psutil
import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="yourusername",
  password="yourpassword",
  database="mydatabase"
)

mycursor = mydb.cursor()

hdd = psutil.disk_usage('/')

sql = "INSERT INTO stats (name, used, free, cpu) VALUES (%s, %d, %d, %d)"
val = ("DB01", (hdd.used // (2**30)),(hdd.free // (2**30)), psutil.cpu_percent(interval=1))
mycursor.execute(sql, val)

mydb.commit()

# print("Total: %d GiB" % (hdd.total // (2**30)))
# print("Used: %d GiB" % (hdd.used // (2**30)))
# print("Free: %d GiB" % (hdd.free // (2**30)))

# print(psutil.cpu_percent(interval=1))

#print(psutil.cpu_percent(interval=4))#get cpu usage for 4 seconds

#harddrive
#cpu
#beschikbaarheid
#online offline
