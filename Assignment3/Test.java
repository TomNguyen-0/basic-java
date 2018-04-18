

import java.util.Scanner;

import data_structures.HashI;
import data_structures.RedBlackI;
import data_structures.RedBlackTree;
import dns_resolver.IPAddress;
import dns_resolver.URL;
import exceptions.FileFormatException;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub//			hello2.add(3,request);
		//		hello2.add(2,new URL("Tom"+n+".sdsu.edu"));
		//		hello2.add(1,new URL("Tom"+n+".sdsu.edu"));
		//		hello2.add(5,new URL("Tom"+n+".sdsu.edu"));
		//		hello2.add(2,request);
		//		hello2.add(4,request);
		//		hello2.add(5,request);
		//	hello.print();





	}

	public static void main(String[] args) {


		//		LoadInternetAddresses loader = new LoadInternetAddresses();
		//
		//		HashI<IPAddress,URL> url2ip = null;
		//		try {
		//			// Change the two lines below to switch between the small and large
		//			// data sets. Use the small data set for initial development and testing.
		//			url2ip = loader.load_addresses("src/data/top-250k.ip");
		//			//url2ip = loader.load_addresses("src/data/ips.txt");
		//		} catch (FileFormatException e) {
		//			System.err.println("There was a file format exception");
		//			e.printStackTrace();
		//		}
		//
		//		System.out.print("Please enter a URL to find the IP address for. To exit, ");
		//		System.out.println("type quit and press return\nFor example:\nURL > edwards.sdsu.edu");
		//		IPAddress request = new IPAddress("192.168.0.10"); 
		//		if (url2ip.contains(request))
		//			System.out.println("The IP address for " + request + " is " + url2ip.getValue(request));
		//		else
		//			System.out.println("Error 404: The URL " + request + " is unknown");
		//
		//		while (true){
		//			System.out.print("URL > ");
		//			@SuppressWarnings("resource")
		//			String ask = new Scanner(System.in).nextLine();
		//			if (ask.equals("quit"))
		//				System.exit(0);
		//			IPAddress tofind = new IPAddress(ask);
		//			if (url2ip.contains(tofind))
		//				System.out.println("The IP address for " + tofind + " is " + url2ip.getValue(tofind));
		//			else
		//				System.out.println("Error 404: The URL " + tofind + " is unknown");
		//		}


				RedBlackTree<IPAddress,URL> hello = new RedBlackTree<>();
		//		RedBlackTree<Integer,URL> hello2 = new RedBlackTree<>();
		int n =1,c=0;
		RedBlackTree<Integer, URL> hello3 = new RedBlackTree<>();
		RedBlackI<Integer,URL> hello4 = new RedBlackTree<Integer,URL>();
		for(int i=1; i<9;i++,n++,c++){
			URL request = new URL ("Tom"+c+".sdsu.edu");
//			hello4.add(i,request);

//						hello3.add(i, request);

			//			if(i%2==0)
			//				n = n+ 3*i;
						IPAddress ip = new IPAddress("192.168.0."+n);//not printing correct ipaddress. something is wrong with compareto. TODO
						hello.add(ip,request );
		}
//		hello4.print();
		//		hello.add(new IPAddress("192.168.0.15"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.10"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.20"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.18"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.25"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.17"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.19"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.16"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.15"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.10"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.16"), new URL("Hello"));
//				hello.add(new IPAddress("192.168.0.8"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.12"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.11"), new URL("Hello"));
		//		hello.add(new IPAddress("192.168.0.6"), new URL("Hello"));
//				hello.add(new IPAddress("192.168.0.8"), new URL("Hello"));
//				hello.add(new IPAddress("192.168.0.9"), new URL("Hello"));
//				hello.add(new IPAddress("192.168.0.10"), new URL("Hello"));

hello.print();
		//		System.out.println("contains: "+hello.contains(new IPAddress("192.168.0.18")));
		//		System.out.println("size: "+hello.size());
		//		System.out.println("height: "+hello.height());
		//		System.out.println("isempty: "+hello.isEmpty());
		//		System.out.println("getValue: "+hello.getValue(new IPAddress("192.168.0.12")));
		//this crashed my program: 13 8 14 6 7 9 10 12


		//		Iterator<Integer> it = r.iterator();
		//		while(it.hasNext())
		//			System.out.println(it.next().tostring()+"");
		//		hello3.print();


		//on blackboard. does not say to copy hashListI TODO
		//how am i supposed to test the red and black tree.
	}

}
