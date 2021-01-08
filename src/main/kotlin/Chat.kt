data class Chat(
    val chatId: Int,
    val messages: MutableList<Message>,
    val lastMessageId: Int,
    val amountOfMessages: Int = 0,
    val isRead: Boolean
)