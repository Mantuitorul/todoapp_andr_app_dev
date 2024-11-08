import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User
import java.util.Date

data class ToDo(
    val number: Int = 0,
    val title: String,
    val description: String,
    val createdByUser: User,
    val createdOnDate: Date,
    val assignedToUser: User?,
    val finishedOnDate: Date?,
    val timeEstimated: Int,
    val analysisDone: Boolean = false,
    val developmentDone: Boolean = false,
    val reviewAndTestingDone: Boolean = false,
    val acceptanceDone: Boolean = false
) {
    constructor() : this(0, "", "", User(), Date(), null, null, 0)

    enum class Status {
        NEW, ASSIGNED, FINISHED
    }

    val status: Status
        get() {
            return if (assignedToUser != null && finishedOnDate != null) {
                Status.FINISHED
            } else if (assignedToUser != null) {
                Status.ASSIGNED
            } else {
                Status.NEW
            }
        }

    val statusDescription: String
        get() {
            return when (status) {
                Status.NEW -> "New"
                Status.ASSIGNED -> "Assigned"
                Status.FINISHED -> "Finished"
            }
        }

    val timeRemaining: Double
        get() {
            return when {
                acceptanceDone -> 0.0
                reviewAndTestingDone -> timeEstimated * 0.10
                developmentDone -> timeEstimated * 0.30
                analysisDone -> timeEstimated * 0.85
                else -> timeEstimated.toDouble()
            }
        }
}