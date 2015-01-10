package benchmark.searching;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class SearchEngine {
    private IndexSearcher searcher = null;
    private QueryParser parser = null;
    private Directory index;

    /** Creates a new instance of SearchEngine */
    public SearchEngine(Directory ramDir) throws IOException {
    	index = ramDir;
    	IndexReader reader = DirectoryReader.open(index);
    	searcher = new IndexSearcher(reader);
        parser = new QueryParser("content", new StandardAnalyzer());        
    }

    public TopDocs performSearch(String queryString, int n)
    throws IOException, ParseException {
        Query query = parser.parse(queryString);
        return searcher.search(query, n);
    }

    public Document getDocument(int docId)
    throws IOException {
        return searcher.doc(docId);
    }
}