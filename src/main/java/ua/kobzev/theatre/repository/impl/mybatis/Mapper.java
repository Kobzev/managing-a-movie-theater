package ua.kobzev.theatre.repository.impl.mybatis;

import ua.kobzev.theatre.repository.impl.mybatis.mappers.*;

/**
 * Created by kkobziev on 2/16/16.
 */
public interface Mapper extends UserMapper, EventMapper, AuditoriumMapper,
        AssignedEventMapper, TicketsMapper {
}
