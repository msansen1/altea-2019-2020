public interface PassengerService {
    void pay(Trip trip);
}
class PassengerServiceImpl implements PassengerService {
    private PaymentService paymentService;
    private NotificationService notificationService;
    void setPaymentService(PaymentService service) {
        this.paymentService = service;
    }
    void setNotificationService(NotificationService service) {
        this.notificationService = service;
    }
    void pay(Trip trip){
        this.paymentService.pay(trip.getPrice(), trip.getDriver());
        var event = new TripPaidEvent(trip);
        this.notificationService.notify(event);
    }
}
public interface PaymentService {
    void pay(float price, Driver driver);
}

public interface NotificationService {
    void notify(Event event);
}