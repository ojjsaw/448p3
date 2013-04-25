package relop;

import java.util.ArrayList;

import global.RID;
import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import index.HashScan;

/**
 * Wrapper for hash scan, an index access method.
 */
public class KeyScan extends Iterator{
HashScan hs;
HashIndex index;
SearchKey key;
HeapFile file;
boolean closed;
  /**
   * Constructs an index scan, given the hash index and schema.
   */
ArrayList<Tuple> tuples = new ArrayList<Tuple>();
java.util.Iterator<Tuple> iter;
  public KeyScan(Schema schema, HashIndex index, SearchKey key, HeapFile file) {
    this.setSchema(schema);
	this.index = index;
	this.key = key;
    hs = index.openScan(key);
    if(hs.hasNext()==false){
    	System.out.println("dfgdsfgsg");
    }
    closed = false;
    while(hs.hasNext()){
    	tuples.add(new Tuple(this.getSchema(), file.selectRecord(hs.getNext())));
    }
    iter = tuples.iterator();
  }

  /**
   * Gives a one-line explaination of the iterator, repeats the call on any
   * child iterators, and increases the indent depth along the way.
   */
  public void explain(int depth) {
    //TODO:
  }

  /**
   * Restarts the iterator, i.e. as if it were just constructed.
   */
  public void restart() {
    //hs.close();
    //hs = index.openScan(key);
	  iter = tuples.iterator();
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    return !closed;
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
    iter = null;
	closed = true;
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
    return iter.hasNext();
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() {
	  if(this.hasNext()){
		  return iter.next();
	  }
	  else{
		  throw new IllegalStateException();
	  } 
  }

} // public class KeyScan extends Iterator
