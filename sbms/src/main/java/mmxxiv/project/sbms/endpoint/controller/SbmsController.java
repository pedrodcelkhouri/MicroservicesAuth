package mmxxiv.project.sbms.endpoint.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mmxxiv.project.core.model.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mmxxiv.project.sbms.endpoint.service.SbmsService;

@RestController
@RequestMapping("v1/admin/sbms")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Api(value = "Endpoints")
public class SbmsController {
    private final SbmsService sbmsService;
    @GetMapping
    @ApiOperation(value = "List all", response = Main[].class)
    public ResponseEntity<Iterable<Main>> list(Pageable pageable) {
        return new ResponseEntity<>(sbmsService.list(pageable), HttpStatus.OK);
    }
}
