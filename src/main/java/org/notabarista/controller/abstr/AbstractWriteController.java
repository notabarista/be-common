package org.notabarista.controller.abstr;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabarristaException;
import org.notabarista.service.abstr.IWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public abstract class AbstractWriteController<T extends AbstractEntity, U extends AbstractDTO> extends AbstractReadController<T, U> {

	@Autowired
	private IWriteService<T, U> service;

	@CrossOrigin
	@PostMapping(value = "/insert", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> insert(@NonNull @Valid @RequestBody U dto, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Insert dto");
		}
		if (log.isDebugEnabled()) {
			log.debug("Insert dto:" + dto);
		}

		U d = service.insert(dto);
		List<U> allDtos = Arrays.asList(d);

		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), allDtos, allDtos.size(), 0,
				0, allDtos.size(), ""), HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "/insertList", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> insertList(@NonNull @RequestBody List<U> dtos, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Insert dtos");
		}
		if (log.isDebugEnabled()) {
			log.info("Insert dtos:" + dtos);
		}

		List<U> savedDtos = service.insert(dtos);

		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), savedDtos,
				savedDtos.size(), 0, 0, savedDtos.size(), ""), HttpStatus.OK);
	}

	@CrossOrigin
	@PatchMapping(value = "/update", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> update(@NonNull @RequestBody U dto, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Update dto");
		}
		if (log.isDebugEnabled()) {
			log.debug("Update dto:" + dto);
		}

		U d = service.update(dto);
		List<U> dtos = Arrays.asList(d);

		watch.stop();
		return new ResponseEntity<>(
				new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), dtos, dtos.size(), 0, 0, dtos.size(), ""),
				HttpStatus.OK);
	}

	@CrossOrigin
	@PatchMapping(value = "/updateList", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> update(@NonNull @RequestBody List<U> dtos, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabarristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Update dtos");
		}
		if (log.isDebugEnabled()) {
			log.debug("Update dtos:" + dtos);
		}

		List<U> savedDtos = service.update(dtos);

		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), savedDtos,
				savedDtos.size(), 0, 0, savedDtos.size(), ""), HttpStatus.OK);
	}

}
