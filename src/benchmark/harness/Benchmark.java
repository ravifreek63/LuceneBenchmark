package benchmark.harness;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import benchmark.indexing.Indexer;
import benchmark.searching.SearchEngine;

public class Benchmark {

	public static void main(String[] args) throws IOException, ParseException {
		Indexer indexer = new Indexer();
		indexer.addEntry();
		SearchEngine se = new SearchEngine(indexer.getRamDir());
		se.performSearch("a", 1);
	}

}
