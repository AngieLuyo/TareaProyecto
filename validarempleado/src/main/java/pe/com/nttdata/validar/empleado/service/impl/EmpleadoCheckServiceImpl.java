package pe.com.nttdata.validar.empleado.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.validar.empleado.service.IEmpleadoCheckService;
import pe.com.nttdata.validar.empleado.dao.IEmpleadoCheckDao;
import pe.com.nttdata.validar.empleado.model.EmpleadoCheck;

import java.util.Date;

@Service
@AllArgsConstructor
public class EmpleadoCheckServiceImpl implements IEmpleadoCheckService {

    private final IEmpleadoCheckDao empleadoCheckDao;
    public boolean esEmpleadoFraudulento(Integer empleadoId) {
        empleadoCheckDao.save(
                EmpleadoCheck.builder()
                        .empleadoId(empleadoId)
                        .esEstafador(false)
                        .fechaCreacion(new Date())
                        .build()
        );
        return false;
    }
}

