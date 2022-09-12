package pe.com.nttdata.empleadofeign.notificacion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("notificacion")
@FeignClient(
        name = "notificacion",
        url = "${empleadofeign.notificacion.url}"
)
public interface NotificacionClient {
    @PostMapping(path = "api/v1/notificacion")
    void enviarNotificacion(@RequestBody NotificacionRequest notificacionRequest);
}
