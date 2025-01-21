package com.rostylka.newlib.services.implementations;

import com.rostylka.newlib.dto.ReaderLogDto;
import com.rostylka.newlib.mappers.ReaderLogMapper;
import com.rostylka.newlib.models.ReaderLog;
import com.rostylka.newlib.models.User;
import com.rostylka.newlib.repositories.ReaderLogRepository;
import com.rostylka.newlib.services.ReaderLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderLogServiceImplementation implements ReaderLogService {


    private ReaderLogRepository readerLogRepository;

    /**
     * Constructor
     * @param readerLogRepository - ReaderLog Repository
     */
    public ReaderLogServiceImplementation(ReaderLogRepository readerLogRepository){
        this.readerLogRepository = readerLogRepository;
    }

    /**
     * Method for creation ReaderLog in DataBase
     * @param readerLogDto - ReaderLog DTO
     * @return ReaderLog() create new ReaderLog in DataBase
     */
    @Override
    public ReaderLogDto createReaderLog(ReaderLogDto readerLogDto) {
        ReaderLog readerLog = ReaderLogMapper.mapToReaderLog(readerLogDto);
        ReaderLog createdReaderLog = readerLogRepository.save(readerLog);
        return ReaderLogMapper.mapToReaderLogDto(createdReaderLog);
    }

    /**
     * Method for reading all ReaderLogs from DataBase
     * @return List of ReaderLogDTO from DataBase
     */
    @Override
    public List<ReaderLogDto> readAllReaderLogs() {
        return ReaderLogMapper.mapToReaderLogDtoList(readerLogRepository.findAll());
    }

    /**
     * Method for reading ReaderLog by ID from DataBase
     * @param id ID of ReaderLog
     * @return ReaderLog by ID
     */
    @Override
    public ReaderLogDto readReaderLogById(int id) {
        return ReaderLogMapper.mapToReaderLogDto(readerLogRepository.getReferenceById(id));
    }

    /**
     * Method for Updating ReaderLog in DataBase
     * @param readerLogDto
     * update ReaderLog in DataBase
     */
    @Override
    public ReaderLogDto updateReaderLog(ReaderLogDto readerLogDto) {
        ReaderLog updatedReaderLog = readerLogRepository.getReferenceById(readerLogDto.getId());
        updatedReaderLog.setBook(readerLogDto.getBook());
        updatedReaderLog.setUser(readerLogDto.getUser());
        updatedReaderLog.setDateOut(readerLogDto.getDateOut());
        updatedReaderLog.setDateIn(readerLogDto.getDateIn());
        return ReaderLogMapper.mapToReaderLogDto(readerLogRepository.save(updatedReaderLog));
    }

    /**
     * Method for Deleting ReaderLog from DataBase
     * @param readerLogDto - ReaderLog DTO
     */
    @Override
    public void delete(ReaderLogDto readerLogDto) {
        readerLogRepository.delete(ReaderLogMapper.mapToReaderLog(readerLogDto));
    }

    /**
     * Method for finding all Readers Logs by User
     * @param user - User
     * @return List of Users Logs DTO
     */
    public List<ReaderLogDto> findByUser(User user) {
        return ReaderLogMapper.mapToReaderLogDtoList(readerLogRepository.findByUser(user));
    }

    /**
     * Method for finding all Readers Logs by User where Date In is null (All reading books(Not returned);
     * @param user - User
     * @return - List of Users Logs DTO
     */
    public List<ReaderLogDto> findByUserAndDateInIsNull(User user) {
        return ReaderLogMapper.mapToReaderLogDtoList(readerLogRepository.findByUserAndDateInIsNull(user));
    }


}

