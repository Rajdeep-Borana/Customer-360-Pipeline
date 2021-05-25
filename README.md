# Customer-360-Pipeline
🦖PROBLEM :-->
===========
==>Customer 360 team have problem to handle all customer information and Update it and fault tolerance
•Closed Orders against the cluster (The "ORDERS" Data is provided by Third party between 5PM to 6PM)
 Orders_file.csv -> S3 buckets -> 5 PM -6 PM
•Cstomer related information ("CUSTOMERS-INFORMATION" IS PRESENT ON RELATIONAL DATABASE LIKE :- MySQL ) 
•CRM team all the customer information in a MySQL/oracle_db
S3("Orders")
MySql("Customer_Info")
 • Order filtering on the "CLOSED" orders
 • Load Both the dataset in Hive
 • Notification & HBase data loading

🐝SOLUTION :--> 
===========
S3 Files(https connection) [In AIRFLOW]
HTTP Sensor
Connection - Name - Host/Port/Username/Password/schema
SSH into edgeNode
	1. Download the files from S3 into Local(edgeNode)
	2. Sqoop will fetch the Customers_Info from MySql and Dump to Hive
	3. Upload S3 orders file to HDFS location
	4. Spark program <jar> <input path> <output path> [SUBMIT SPARK~JOB]
	5. Create Hive table from the Output Path available in step 4
	6. Upload it into Hbase (HBase Hive Connectors)
	7. Slack #channel for communication
	Success/Failure of the PipeLine
