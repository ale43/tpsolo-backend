package isi.deso.tpsolo.dto;

public class ReservaRequestDTO {
    private String huespedDni;
    private Long habitacionId; 
    private String fechaInicio;
    private String fechaFin;

    public ReservaRequestDTO() {
    }

    public String getHuespedDni() { 
        return huespedDni; 
    }
    public void setHuespedDni(String huespedDni) { 
        this.huespedDni = huespedDni; 
    }

    public Long getHabitacionId() { 
        return habitacionId; 
    }
    public void setHabitacionId(Long habitacionId) { 
        this.habitacionId = habitacionId; 
    }

    public String getFechaInicio() { 
        return fechaInicio; 
    }
    public void setFechaInicio(String fechaInicio) { 
        this.fechaInicio = fechaInicio; 
    }

    public String getFechaFin() { 
        return fechaFin; 
    }
    public void setFechaFin(String fechaFin) { 
        this.fechaFin = fechaFin; 
    }
}