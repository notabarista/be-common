package org.notabarista.controller.abstr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.abstr.IReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public abstract class AbstractReadController<T extends AbstractEntity, U extends AbstractDTO> {

	@Autowired
	private IReadService<T, U> service;

	@CrossOrigin
	@GetMapping(value = "/findAll", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> findAll(Pageable pageable, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Find all");
		}
		if (log.isDebugEnabled()) {
			log.debug("Find all pageable: " + pageable);
		}

		if (pageable.isUnpaged() && pageable.getSort().isUnsorted()) {
			if (log.isInfoEnabled()) {
				log.info("Unsorted and unpaged");
			}
			List<U> allDtos = service.findAll();
			watch.stop();
			return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), allDtos,
					allDtos.size(), 0, 1, allDtos.size(), ""), HttpStatus.OK);
		}
		if (pageable.isUnpaged() && pageable.getSort().isSorted()) {
			List<U> allDtos = service.findAllSorted(pageable.getSort());
			watch.stop();
			return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), allDtos,
					allDtos.size(), 0, 1, allDtos.size(), ""), HttpStatus.OK);
		}

		Page<U> allDtos = service.findAll(pageable);
		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), allDtos.getContent(),
				allDtos.getTotalElements(), allDtos.getPageable().getPageNumber(), allDtos.getTotalPages(),
				allDtos.getNumberOfElements(), ""), HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/findById/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> findById(@NonNull @PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Find by id: " + id);
		}
		List<U> dtos = new ArrayList<>();

		U dto = service.findById(id);
		dtos.add(dto);

		watch.stop();
		return new ResponseEntity<>(
				new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), dtos, dtos.size(), 1, 0, dtos.size(), ""),
				HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/findAllByEntity", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> findAllByEntity(@NonNull @RequestBody U entity, Pageable pageable,
			HttpServletRequest request, HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Find by dto: " + entity.getId());
		}
		if (log.isDebugEnabled()) {
			log.debug("Find by entity:" + entity);
			log.debug(pageable);
		}

		Page<U> allDtos = service.findByDTO(entity, pageable);
		
		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), allDtos.getContent(),
				allDtos.getTotalElements(), allDtos.getPageable().getPageNumber(), allDtos.getTotalPages(),
				allDtos.getNumberOfElements(), ""), HttpStatus.OK);
	}

}
