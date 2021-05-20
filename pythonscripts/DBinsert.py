import psutil #pip install psutil
# import mysql.connector

# mydb = mysql.connector.connect(
#   host="localhost",
#   user="replication",
#   password="NerdyG@dgets@202!",
#   database="nerdygadgets"
# )

# mycursor = mydb.cursor()

hdd = psutil.disk_usage('/')

sql = "INSERT INTO stats (name, used, free, cpu) VALUES (%s, %d, %d, %d)"
val = ("DB01", (hdd.used // (2**30)),(hdd.free // (2**30)), round(psutil.cpu_percent(interval=1)))
print(val);
# mycursor.execute(sql, val)

# mydb.commit()

# mydb.close();
