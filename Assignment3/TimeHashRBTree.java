package timeDataStructures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import data_structures.Hash;
import data_structures.HashI;
import data_structures.RedBlackI;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;

/**
 * TimeHashRBTree prints the time it takes to load data into
 * the RedBlackTree data structure.
 * 
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 */
public class TimeHashRBTree {
	static String [] values;
	static IPAddress ip;
	static URL url;
	static RedBlackI<IPAddress,URL> redBlackTree = new RedBlackTree<>();
	static HashI<IPAddress,URL> hashTable = new Hash<>(3000000);
	static HashMap<IPAddress,URL> javaHash = new HashMap<>();
	static TreeMap<IPAddress,URL> javaMap = new TreeMap<>();
	static String fileName = "src/data/top-1m.ip";

	/**
	 * The main method to printing the time in m/s
	 * it takes to load data into the RedBlack Tree.
	 * @param args the argument.
	 */
	public static void main(String[] args) {
		myHash();
		search(hashTable);
		hashTable = new Hash<>();
		System.out.println();
		myRedBlack();
		search(redBlackTree);
		redBlackTree =new RedBlackTree<>();
		System.out.println();
		javaHashMap();
		search(javaHash);
		javaHash = new HashMap<>();
		System.out.println();
		javaTree();
		search(javaMap);
		javaMap = new TreeMap<>();
	}

	/**
	 * This method searches the data structure and returns
	 * the time it took to find all the data.
	 * @param name the key that will be used to search.
	 * @param tree the data structure that is being used.
	 */
	public static void search(Object tree){
		long start=0, stop = 0,timed;	
		if(tree.getClass()== javaHash.getClass()){
			start = System.currentTimeMillis();
			readForSearch(javaHash);
			stop = System.currentTimeMillis();
		}
		else if(tree.getClass()== redBlackTree.getClass()){
			start = System.currentTimeMillis();
			readForSearch(redBlackTree);
			stop = System.currentTimeMillis();
		}
		else if(tree.getClass()== javaMap.getClass()){
			start = System.currentTimeMillis();
			readForSearch(javaMap);
			stop = System.currentTimeMillis();
		}
		else if(tree.getClass()== hashTable.getClass()){
			start = System.currentTimeMillis();
			readForSearch(hashTable);
			stop = System.currentTimeMillis();
		}
		timed = stop - start;
		System.out.print(" timed: ");
		System.out.println(timed);
	}

	/**
	 * Reads one line at a time from the file.
	 * Then searches it in the data structure.
	 * prints the time it took to search for
	 * all the data from the file.
	 * @param tree the data Structure.
	 */
	public static void readForSearch(Object tree){
		try{
			FileReader infile = new FileReader(fileName);
			BufferedReader br = new BufferedReader(infile);
			String line;
			if(tree.getClass()== hashTable.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					hashTable.contains(ip);
				}
			else if(tree.getClass()== redBlackTree.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					redBlackTree.contains(ip);
				}
			else if(tree.getClass()== javaHash.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					javaHash.containsKey(ip);
				}
			else if(tree.getClass()== javaMap.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					javaMap.containsKey(ip);
				}
			System.out.print("searched for everything. ");
			infile.close();
		} catch (FileNotFoundException e) {
			System.err.println("There was a file format exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("There was an error with the reading");
			e.printStackTrace();
		}
	}

	/**
	 * prints out the time it takes for myRedBlack to load.
	 */
	public static void myRedBlack(){
		long start = System.currentTimeMillis();
		read(fileName,redBlackTree);
		long stop = System.currentTimeMillis();
		long timed = stop - start;
		System.out.println("my RedBlackTree timed: "+ timed);
	}

	/**
	 * prints out the time it takes for myHash to load.
	 */
	public static void myHash(){
		long start = System.currentTimeMillis();
		read(fileName,hashTable);
		long stop = System.currentTimeMillis();
		long timed = stop - start;
		System.out.println("my Hash table   timed: "+ timed);
	}

	/**
	 * prints out the time it takes for javaHashMap to load.
	 */
	public static void javaHashMap(){
		long start = System.currentTimeMillis();
		read(fileName,javaHash);
		long stop = System.currentTimeMillis();
		long timed = stop - start;
		System.out.println("Java Hash       timed: "+ timed);
	}

	/**
	 * prints out the time it takes for javaTree to load.
	 */
	public static void javaTree(){
		long start = System.currentTimeMillis();
		read(fileName,javaMap);
		long stop = System.currentTimeMillis();
		long timed = stop - start;
		System.out.println("Java tree       timed: "+ timed);
	}

	/**
	 * Loads the data from a file into the data structure.
	 * @param filename the file that is being read from.
	 * @param tree the data structure that is being used.
	 */
	@SuppressWarnings("unchecked")
	public static void read(String filename,Object tree){
		try {
			FileReader infile = new FileReader(filename);
			BufferedReader br = new BufferedReader(infile);
			String line;
			if(tree.getClass()== redBlackTree.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					url = new URL(values[0]);
					((RedBlackTree<IPAddress, URL>) tree).add(ip,url);
				}
			else if(tree.getClass()== hashTable.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					url = new URL(values[0]);
					((HashI<IPAddress,URL>) tree).add(ip,url);
				}
			else if(tree.getClass()== javaHash.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					url = new URL(values[0]);
					((HashMap<IPAddress,URL>) tree).put(ip,url);
				}
			else if(tree.getClass()== javaMap.getClass())
				while((line= br.readLine()) != null){
					values = line.split("\t");
					ip = new IPAddress(values[1]);
					url = new URL(values[0]);
					((TreeMap<IPAddress,URL>)tree).put(ip, url);
				}
			infile.close();
		} catch (FileNotFoundException e) {
			System.err.println("There was a file format exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("There was an error with the reading");
			e.printStackTrace();
		}
	}
}
