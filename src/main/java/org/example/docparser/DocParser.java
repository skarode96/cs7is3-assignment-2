package org.example.docparser;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.io.IOException;
import java.util.List;

interface DocParser {

    /**
     * Parse a SGML file and returns a collection terms
     * @param absPathToFiles absolute path of the files to be parsed
     * @return Collection of lucene Documents parsed from the input file.
     * @throws IOException if an I/O error occurs
     **/
    List<Document> parse(String absPathToFiles) throws IOException;


    /**
     * Method to create Lucene Document.
     * @param docNo .
     * @param header .
     * @param text .
     * @return an instance of {@link org.apache.lucene.document.Document} with docno, header and text as the indexable fields.
     **/
    static Document createDocument(String docNo, String header, String text){
        Document document = new Document();
        document.add(new StringField("docno", docNo, Field.Store.YES));
        document.add(new TextField("header", header, Field.Store.YES) );
        document.add(new TextField("text", text, Field.Store.YES) );
        return document;
    }
}
