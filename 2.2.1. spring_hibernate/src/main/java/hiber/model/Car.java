package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(carId, car.carId) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, model, series);
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}