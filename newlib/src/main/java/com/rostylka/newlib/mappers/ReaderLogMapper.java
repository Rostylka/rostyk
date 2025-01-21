package com.rostylka.newlib.mappers;

import com.rostylka.newlib.dto.ReaderLogDto;
import com.rostylka.newlib.models.ReaderLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for transforming ReaderLog into ReaderLogDTO and  vice versa
 */
public class ReaderLogMapper {
    /**
     * Method for transforming ReaderLog into ReaderLogDTo
     * @param readerLog - ReaderLog
     * @return ReaderLogDTO - ReaderLog DTO
     */
    public static ReaderLogDto mapToReaderLogDto(ReaderLog readerLog) {
        return new ReaderLogDto(readerLog.getId(),
                readerLog.getUser(),
                readerLog.getBook(),
                readerLog.getDateOut(),
                readerLog.getDateIn());
    }

    /**
     * Method for transforming ReaderLogDTO into ReaderLog
     * @param readerLogDto - ReaderLog DTO
     * @return ReaderLog - ReaderLog
     */
    public static ReaderLog mapToReaderLog(ReaderLogDto readerLogDto) {
        return new ReaderLog(readerLogDto.getId(),
                readerLogDto.getUser(),
                readerLogDto.getBook(),
                readerLogDto.getDateOut(),
                readerLogDto.getDateIn());
    }

    /**
     * Method for transforming ReaderLog list into ReaderLog DTO list
     * @param readerLogs list of ReaderLogs
     * @return list of ReaderLogs DTO
     */
    public static List<ReaderLogDto> mapToReaderLogDtoList(List<ReaderLog> readerLogs) {
        List<ReaderLogDto> dtoReaderLogs = new ArrayList<>();
        for (ReaderLog readerLog : readerLogs) {
            dtoReaderLogs.add(ReaderLogMapper.mapToReaderLogDto(readerLog));
        }
        return dtoReaderLogs;
    }

    /**
     * Method for transforming ReaderLog DTO list into ReaderLog list
     * @param dtoReaderLogs list of ReaderLogs
     * @return list of ReaderLogs
     */
    public static List<ReaderLog> mapToReaderLogList(List<ReaderLogDto> dtoReaderLogs) {
        List<ReaderLog> readerLog = new ArrayList<>();
        for (ReaderLogDto dtoReaderLog : dtoReaderLogs) {
            readerLog.add(ReaderLogMapper.mapToReaderLog(dtoReaderLog));
        }
        return readerLog;
    }
}
