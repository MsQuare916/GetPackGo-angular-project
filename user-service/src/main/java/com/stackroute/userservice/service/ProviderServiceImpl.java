package com.stackroute.userservice.service;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.exceptions.ProviderAlreadyExistsException;
import com.stackroute.userservice.exceptions.ProviderNotFoundException;
import com.stackroute.userservice.model.provider.Provider;
import com.stackroute.userservice.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderRepository providerRepository;
	
	@Override
	public Provider getProviderByEmailId(String emailId) throws ProviderNotFoundException {
		if (providerRepository.existsById(emailId)) {
			return providerRepository.findById(emailId).get();
		}
		throw new ProviderNotFoundException();
	}

	@Override
	public String deleteProviderByEmailId(String emailId) throws ProviderNotFoundException {
		if (providerRepository.existsById(emailId)) {
			providerRepository.deleteById(emailId);
			return "Sucessfull!!";
		}
		throw new ProviderNotFoundException();
	}

	@Override
	public String addProvider(Provider provider) throws ProviderAlreadyExistsException {
		if (!providerRepository.existsById(provider.getPrimaryMail())) {
			providerRepository.save(provider);
			return "Successfully added provider!!";
		}
		throw new ProviderAlreadyExistsException();
	}

	@Override
	public List<Provider> findAllProviderAsList() {
		return providerRepository.findAll();
	}

	@Override
	public String saveOrUpdateProvider(Provider provider) throws ProviderNotFoundException {
		if (providerRepository.existsById(provider.getPrimaryMail())) {
			providerRepository.save(provider);
			return "Successfully updated the changes!!";
		}
		throw new ProviderNotFoundException();
	}

	@Override
	public String addProfilePicLocation(MultipartFile profilePic, String emailId)
			throws ProviderAlreadyExistsException, IOException {
			if (providerRepository.existsById(emailId)) {
				Provider provider = providerRepository.findById(emailId).get();
				if (provider.getProfilePic() != null) {
					provider.setProfilePic(new String(Base64.encodeBase64(profilePic.getBytes())));
					providerRepository.save(provider);
					return "Successfully added photo to profile";					
				}
			}
		throw new ProviderAlreadyExistsException();
	}

	@Override
	public String saveOrUpdateProfilePic(MultipartFile profilePic, String emailId)
			throws ProviderNotFoundException, IOException {
		if (providerRepository.existsById(emailId)) {
			Provider provider = providerRepository.findById(emailId).get();
			provider.setProfilePic(new String(Base64.encodeBase64(profilePic.getBytes())));
			providerRepository.save(provider);
			return "Successfully updatedd the profile photo";
		}
		throw new ProviderNotFoundException();
	}

}
