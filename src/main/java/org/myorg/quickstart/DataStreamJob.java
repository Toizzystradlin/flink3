
package org.myorg.quickstart;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.myorg.quickstart.entities.CityPojo;
import org.myorg.quickstart.entities.Enriched;
import org.myorg.quickstart.joins.Join1;
import org.myorg.quickstart.sources.CsvSource1;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {
		// Sets up the execution environment, which is the main entry point
		// to building Flink applications.
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		CsvSource1 csvSource1 = new CsvSource1("/home/nksokolov/data/");
		FileSource<CityPojo> stream_source = csvSource1.get();
		DataStream<CityPojo> stream = env.fromSource(stream_source, WatermarkStrategy.noWatermarks(), "source-1");

		DataStream<Enriched> rich_stream = stream.flatMap(new Join1());

//		stream.join(stream).where(stream.);

		rich_stream.print();

		env.execute("read filesource");
	}
}
