package benchmark.harness;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import benchmark.indexing.Indexer;
import benchmark.searching.SearchEngine;

public class Benchmark {
	public static void main(String[] args) throws IOException, ParseException {
		Indexer indexer = new Indexer();
		indexer.addEntry();
		SearchEngine se = new SearchEngine(indexer.getRamDir());
		ScoreDoc[]hits = se.performSearch("a b c", 1);
		System.out.println("Found " + hits.length + " hits.");
		for(int i=0;i<hits.length;++i) {
		 Document d = se.getDocument(hits[i].doc);
		 System.out.println((i + 1) + ". " + d.get("title"));
		}
		
	}
}
