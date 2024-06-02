
package org.myorg.quickstart;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.myorg.quickstart.entities.CityPojo;
import org.myorg.quickstart.entities.Enriched;
import org.myorg.quickstart.joins.MyEnrichJoin;
import org.myorg.quickstart.sources.MyCsvSource;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {
		// Sets up the execution environment, which is the main entry point
		// to building Flink applications.
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		MyCsvSource myCsvSource = new MyCsvSource("/root/data/");
		FileSource<CityPojo> stream_source = myCsvSource.get();
		DataStream<CityPojo> stream = env.fromSource(stream_source, WatermarkStrategy.noWatermarks(), "source-1");

		DataStream<Enriched> rich_stream = stream.flatMap(new MyEnrichJoin("/root/data/sights.csv"));

		rich_stream.print();

		env.execute("read filesource");
	}
}
