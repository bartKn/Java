package pl.bartkn.codesharing.code;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "snippets")
public class Code {

    @Column(name = "code")
    private String code;

    @Column(name = "creation_time")
    private LocalDateTime date;

    @JsonIgnore
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @JsonIgnore
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Transient
    private long time;

    @Column(name = "views_left")
    private long views;

    @JsonIgnore
    @Column(name = "time_flag")
    boolean timeFlag = false;

    @JsonIgnore
    @Column(name = "views_flag")
    boolean viewsFlag = false;

    public Code(UUID id, String code, LocalDateTime timestamp, long time, long views) {
        this.id = id;
        this.code = code;
        this.date = timestamp;
        this.time = time;
        this.views = views;
    }

    public Code(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }

    public Code() {
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    @JsonIgnore
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public String getStringId() {
        return String.valueOf(id);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        if (time > 0) {
            this.timeFlag = true;
            this.expiryDate = date.plusSeconds(time);
        }
    }

    public void updateTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
        if (views > 0) {
            this.viewsFlag = true;
        }
    }

    public boolean isTimeFlag() {
        return timeFlag;
    }

    public boolean isViewsFlag() {
        return viewsFlag;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", time=" + time +
                ", views=" + views +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        if (!code.equals(code1.code)) return false;
        if (!date.equals(code1.date)) return false;
        return id.equals(code1.id);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
