package com.project.catalog.catalog.services;

import com.project.catalog.catalog.dto.ProductDTO;
import com.project.catalog.catalog.dto.RoleDTO;
import com.project.catalog.catalog.dto.UserDTO;
import com.project.catalog.catalog.dto.UserInsertDTO;
import com.project.catalog.catalog.entities.Product;
import com.project.catalog.catalog.entities.Role;
import com.project.catalog.catalog.entities.User;
import com.project.catalog.catalog.repositories.ProductRepository;
import com.project.catalog.catalog.repositories.RoleRepository;
import com.project.catalog.catalog.repositories.UserRepository;
import com.project.catalog.catalog.services.exceptions.DataBaseException;
import com.project.catalog.catalog.services.exceptions.ResourceNotFoundExeption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);

		return list.map(x -> new UserDTO(x));

	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {

		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundExeption("Entity not Found"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {

		User entity = new User();
		copyDtoToEntity(entity, dto);
		entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);

	}

	private void copyDtoToEntity(User entity, UserDTO dto) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());

		entity.getRoles().clear();
		for (RoleDTO roleDTO : dto.getRoles()){
			Role role = roleRepository.getOne(roleDTO.getId());
			entity.getRoles().add(role);
		}

	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			User entity = repository.getOne(id);
			copyDtoToEntity(entity, dto);
			entity = repository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExeption("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExeption("Id not found" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");

		}

	}
}
