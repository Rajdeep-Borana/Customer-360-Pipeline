import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext

object Filter_S3_Orders {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.WARN)

    val sc = new SparkContext()

    val base_rdd = sc.textFile(args(0))

    val final1 = base_rdd.map(x => (x.split(",")(0), x.split(",")(1), x.split(",")(2), x.split(",")(3)))

    val Filter_Rdd = final1.filter(x => x._4 == "CLOSED").map(x => x._1 + "," + x._2 + "," + x._3 + "," + x._4)
      .repartition(1)
      .saveAsTextFile("/user/cloudera/Filter_S3_Orders/")

  }
}


SUBMIT JOB :-->

spark-submit --class Filter_Orders Airflow_bundell.jar airflow_input/orders.csv airflow_output
