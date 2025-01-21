package com.rostylka.newlib.services;

import com.rostylka.newlib.dto.ReaderLogDto;

import java.util.List;

/** Reader Loging Service interface
 *
 */
public interface ReaderLogService {
    /**
     * Method for creation ReaderLog in DataBase
     * @param readerLogDto - ReaderLog DTO
     * @return ReaderLog() create new ReaderLog in DataBase
     */
    ReaderLogDto createReaderLog(ReaderLogDto readerLogDto);

    /**
     * Method for reading all ReaderLogs from DataBase
     * @return List of ReaderLogDTO from DataBase
     */
    List<ReaderLogDto> readAllReaderLogs();

    /**
     * Method for reading ReaderLog by ID from DataBase
     * @param id ID of ReaderLog
     * @return ReaderLog by ID
     */
    ReaderLogDto readReaderLogById(int id);

    /**
     * Method for Updating ReaderLog in DataBase
     * @param readerLogDto - ReaderLog DTO
     * @return updated ReaderLog
     */
    ReaderLogDto updateReaderLog(ReaderLogDto readerLogDto);

    /**
     * Method for Deleting ReaderLog from DataBase
     * @param readerLogDto - ReaderLog DTO
     */
    void delete(ReaderLogDto readerLogDto);
}

