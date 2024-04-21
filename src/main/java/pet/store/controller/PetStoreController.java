package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
        
}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@RequestBody PetStoreData petStoreData, @PathVariable Long petStoreId) {
        petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
        
}
	
	@PostMapping("/pet_store/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId, 
                                                  @RequestBody PetStoreEmployee employee) {
        log.info("Received request to add employee to pet store with ID: {}", petStoreId);
        return petStoreService.saveEmployee(petStoreId, employee);
	

}
	
	@PostMapping("/pet_store/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId, 
                                                  @RequestBody PetStoreCustomer customer) {
        log.info("Received request to add customer to pet store with ID: {}", petStoreId);
        return petStoreService.saveCustomer(petStoreId, customer);
	
	}
	
	 @GetMapping
	    public List<PetStoreData> retrieveAllPetStores() {  
		 return petStoreService.retrieveAllPetStores();	 
	 }
	 
	 @GetMapping("/pet_store/{petStoreId}")
	    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
	     log.info("Retrieving petStore with ID={}", petStoreId);
		 return petStoreService.retrievePetStoreById(petStoreId);
}
	 
	 @DeleteMapping("/pet_store")
	 public void deletePetStoreById() {
		 log.info("Attempting to delete all petStores");
		 throw new UnsupportedOperationException(
				 "Deleting all petStores is not allowed.");
		 
	 }

	 @DeleteMapping("/pet_store/{petStoreId}")
	 
	 public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId){
		 log.info("Deleting petSTore with ID={}", petStoreId);
		 
		 petStoreService.deletePetStoreById(petStoreId);
		 
		 return Map.of("message", "Deletion of petStore with ID=" + petStoreId + " was successful.");
	 }
	 

}