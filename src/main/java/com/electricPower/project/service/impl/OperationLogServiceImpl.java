package com.electricPower.project.service.impl;

import com.electricPower.project.entity.OperationLog;
import com.electricPower.project.mapper.OperationLogMapper;
import com.electricPower.project.service.IOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com.chaFan
 * @since 2020-10-02
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
