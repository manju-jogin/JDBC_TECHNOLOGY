package hiber;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Student2 {

	public static void main(String[] args) {
	   EntityManagerFactory em=Persistence.createEntityManagerFactory("dev");
	   EntityManager e=em.createEntityManager();
	   EntityTransaction t=e.getTransaction();
	   
	   Scanner sc=new Scanner(System.in);
	   boolean flag=true;
	   while(flag)
	   {
		   System.out.println("===============================================");
		   System.out.println("1].ADD STUDENT");
		   System.out.println("2].FETCH STUDENT");
		   System.out.println("3].UPDATE STUDENT");
		   System.out.println("4].FETCH ALL STUDENT");		   
		   System.out.println("5]REMOVE STUDENT");
		   System.out.println("6].EXIT");
		   System.out.println("enter the option.....!");
		   System.out.println("================================================");
		   System.out.println();
		   switch(sc.nextInt())
		   {
		      case 1:
		      {
			     System.out.println("ENTER THE STUDENT NAME");
			     String name=sc.next();
			     System.out.println("ENTER THE STUDENT USN");
			     String usn=sc.next();
			     System.out.println("ENTER THE STUDENT NUMBER");
			     Long phno=sc.nextLong();
			     System.out.println("ENTER THE STUDENT ADDERSS");
			     String address=sc.next();
			     
			     Student s=new Student();
			     s.setName(name);
			     s.setUsn(usn);
			     s.setAdderss(address);
			     s.setNumber(phno);
			  
			     t.begin();
			     e.persist(s);
			     t.commit(); 
			     System.out.println("");
		      }
		      break;
		      
		      case 2:
		      {
		    	  System.out.println("ENTER THE STUDENT USN");
		    	String usn=sc.next();
		    	Student d=e.find(Student.class, usn);
		    	if(d!=null)
		    	{
		    		System.out.println("=========================");
		    		System.out.println("    STUDENT DETAILS      ");
		    		System.out.println("=========================");
		    		System.out.println("Name: "+d.getName());
		    		System.out.println("Usn: "+d.getUsn());
		    		System.out.println("Address: "+d.getAdderss());
		    		System.out.println("PhoNo: "+d.getNumber());
		    		System.out.println("=========================");
		    	}
		    	else{
		    		System.err.println("SORRY...Invalid USN......!");
		    	}
		    	
		      }
		      break;
		      
		      case 3:
		      {
		    	  
		    	  System.out.println("ENTER THE STUDENT USN");
			    	String usn=sc.next();
			    	Student d=e.find(Student.class, usn);
			    	if(d!=null)
			    	{
			    		t.begin();
			    	   System.out.println("ENTER THE STUDENT NEW NAME");
				       String name=sc.next();
				        System.out.println("ENTER THE STUDENT NEW PHONE NUMBER");
				        Long phno=sc.nextLong();
				        System.out.println("ENTER THE STUDENT NEW ADDERSS");
				        String address=sc.next();
				     
				     d.setName(name);
				     d.setAdderss(address);
				     d.setNumber(phno);
				     
				     e.merge(d);
				     t.commit();
			    	}
		      }
		      break;
		      
		      case 4:
		      {
		    	 
		    	Query f=e.createQuery("select w from Student w"); 
		    	
		    	List l=f.getResultList();
		    	for(Object x:l)
		    	{
		    		 System.out.println("==============================");
			    	  System.out.println("FETCHING ALL DETALIS");
			    	  System.out.println("============================== ");
		    		System.out.println("Name: "+((Student)x).getName());
		    		System.out.println("USN: "+((Student)x).getUsn());
		    		System.out.println("PHONE NUMBER: "+((Student)x).getNumber());
		    		System.out.println("ADDRESS: "+((Student)x).getAdderss());
		    	}
		      }
		      break;
		      
		      case 5:
		      {
		    	  System.out.println("ENTER THE STUDENT USN");
			    	String usn=sc.next();
			    	Student d=e.find(Student.class, usn);
			    	if(d!=null)
			    	{
			    		t.begin();
			    		e.remove(d);
			    		t.commit();
			    	}
			    	else{
			    		System.err.println("Invalid USN");
			    	}
		      }
		      break;
		      
		      case 6:
		      {
		    	  flag=false;
		    	  System.out.println("Thank You.....!");
		      }
		      break;
		      default:
		      {
		    	  System.err.println("invalid option...!");
		      }
		   }
	   }

	}
}
