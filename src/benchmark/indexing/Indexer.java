package benchmark.indexing;

import java.io.IOException;
import java.io.StringReader;
import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;	
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

    /** Creates a new instance of Indexer */
    public Indexer() {}

    private IndexWriter indexWriter = null;
    private Directory index ;
    
    public Directory getRamDir() {
    	return index;
    }

    public IndexWriter getIndexWriter(boolean create) throws IOException {
        if (indexWriter == null) {
        	IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
        	index = new RAMDirectory(); // This creates an index in the RAMDirectory            
            indexWriter = new IndexWriter(index, config);
        }
        return indexWriter;
   }

    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
   }

    public void addEntry() throws IOException {
        IndexWriter writer = getIndexWriter(false);
        Document doc = new Document();
        doc.add(new StringField("id", "a", Field.Store.YES));
        doc.add(new StringField("name", "b", Field.Store.YES));
        doc.add(new StringField("city", "c", Field.Store.YES));
        doc.add(new TextField("content", "abc", Field.Store.NO));
        writer.addDocument(doc);
    }

    public void rebuildIndexes() throws IOException {
          //
          // Erase existing index
          //
          getIndexWriter(true);
          //
          // Index all Accommodation entries
          //
          //Hotel[] hotels = HotelDatabase.getHotels();
          //for(Hotel hotel : hotels) {
            //  indexHotel(hotel);
          //}
          //
          // Don't forget to close the index writer when done
          //
          closeIndexWriter();
     }
}
