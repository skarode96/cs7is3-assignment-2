package ie.tcd.newssearch.docloader;

import org.apache.lucene.document.Document;

import java.io.IOException;
import java.util.List;

public interface DocLoader {

    /**
     * Parse a SGML file and returns a collection terms
     *
     * @param absPathToFiles absolute path of the files to be parsed
     * @return Collection of lucene Documents parsed from the input file.
     * @throws IOException if an I/O error occurs
     **/
    List<Document> load(String absPathToFiles) throws IOException;
}
