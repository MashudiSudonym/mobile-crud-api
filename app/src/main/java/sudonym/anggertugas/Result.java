package sudonym.anggertugas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudonym on 24/03/17.
 */

public class Result {

    Integer id;
    String nama;
    String alamat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

}
