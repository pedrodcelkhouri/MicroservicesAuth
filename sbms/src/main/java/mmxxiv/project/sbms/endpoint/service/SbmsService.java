package mmxxiv.project.sbms.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mmxxiv.project.core.model.Main;
import mmxxiv.project.core.repository.SbmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SbmsService {
    private final SbmsRepository sbmsRepository;
    public Iterable<Main> list (Pageable pageable){
        log.info("Listing all courses");
        return sbmsRepository.findAll(pageable);
    }
}
