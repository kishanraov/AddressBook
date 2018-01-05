package addressbook.springframework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import addressbook.springframework.domain.Address;
import addressbook.springframework.repositories.AddressRepository;

@SpringBootApplication
public class SpringBootMySqlApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootMySqlApplication.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Value("${csvfile:/tmp/data.csv}")
	String csvfile;
		
	public static void main(String[] args) {
		
		StringBuilder Usage = new StringBuilder("Optional Params: --csvfile=provide full path of data.csv including name of the file default is /tmp/data.csv \n");

    					  
    	if (args.length > 0 && args[0].contains("help") ){
    		System.out.println(Usage.toString());
    		return;
    		}
    					     					  
    	if( args.length > 0 && args[0]!= null && args[0].contains("--csvfile=")  ){ 
        	File file =null;
        	if(args[0].contains("--csvfile="))
        	   file = new File(args[0].trim().split("=")[1]);
        	
        	if(!file.exists() || file.isDirectory()) {
        	    System.out.println("Invalid file name or file path:"+ file.getName());
        	    return;
        	}

    	}
    	 	SpringApplication.run(SpringBootMySqlApplication.class,args);
	}
	
@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	        loadCSV();        
	      };
	   }

public void loadCSV(){
	File file = new File(csvfile) ;
	if(!file.exists())
		return;
	
    BufferedReader br = null;
    String line = null;
    int count=0;
    try {

        br = new BufferedReader(new FileReader(csvfile));
        
        while ((line = br.readLine()) != null) {
            // use comma as separator
        	if(count==0)
        		{count ++ ;continue;}
            String[] addr = line.split(",");
            
            Address address = new Address();
             address.setName(addr[0].trim());
            	 address.setPhone(addr[1].trim());
            addressRepository.save(address);
            count++;
        }
        log.info("Total records uploaded:"+count);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
}
