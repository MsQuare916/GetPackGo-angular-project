package com.stackroute.userservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.exceptions.ProviderAlreadyExistsException;
import com.stackroute.userservice.exceptions.ProviderNotFoundException;
import com.stackroute.userservice.model.provider.Provider;

public interface ProviderService {
	public Provider getProviderByEmailId(String emailId) throws ProviderNotFoundException;
	public String deleteProviderByEmailId(String emailId) throws ProviderNotFoundException;
	public String addProvider(Provider provider) throws ProviderAlreadyExistsException;
	public List<Provider> findAllProviderAsList();
	public String saveOrUpdateProvider(Provider provider) throws ProviderNotFoundException;
	public String addProfilePicLocation(MultipartFile profilePic, String emailId) throws ProviderAlreadyExistsException, IOException;
	public String saveOrUpdateProfilePic(MultipartFile profilePic, String emailId) throws ProviderNotFoundException, IOException;
	
}
