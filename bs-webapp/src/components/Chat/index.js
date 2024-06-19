import {
  Widget,
  addUserMessage,
  addResponseMessage,
  renderCustomComponent,
  deleteMessages,
  setBadgeCount,
} from 'react-chat-widget';
import 'react-chat-widget/lib/styles.css';
import './styles.css';
import { useEffect, useRef, useState } from 'react';
import databaseRef from '../../config/firebase';
import moment from 'moment';
import CloseButton from './CloseButton';

const Chat = ({
  subtitle,
  avatar,
  senderId,
  receiverId,
  conversationKey,
  isCompany,
  onCloseChat,
}) => {
  const messageRef = useRef(databaseRef.child(`/chats/${conversationKey}/messages`));
  const badgeRef = useRef(null);
  const receiverBadgeRef = useRef(null);
  const [initing, setIniting] = useState(true);

  const handleSendMessage = (newMessage) => {
    const timestamp = new Date().getTime();
    const newMessageData = {
      message: newMessage,
      senderId: senderId,
      timestamp: timestamp,
    };
    messageRef.current.push(newMessageData);

    badgeRef.current.set(0);

    receiverBadgeRef.current.once('value', (snapshot) => {
      const data = snapshot.val();
      receiverBadgeRef.current.set(data + 1);
    });

    renderCustomComponent(SendTime, {
      timestamp: timestamp,
      isSender: true,
    });
  };

  const fetchMessages = () => {
    messageRef.current.once('value', (snapshot) => {
      const fetchedMessages = [];
      snapshot.forEach((child) => {
        const data = child.val();
        fetchedMessages.push(data);

        if (data['senderId'] === receiverId) {
          addResponseMessage(data['message']);
          renderCustomComponent(SendTime, {
            timestamp: data['timestamp'],
            isSender: false,
          });
        }
        if (data['senderId'] === senderId) {
          addUserMessage(data['message']);
          renderCustomComponent(SendTime, {
            timestamp: data['timestamp'],
            isSender: true,
          });
        }
      });
      setIniting(false);
    });
  };

  useEffect(() => {
    const handleChildAdded = (snapshot) => {
      const data = snapshot.val();

      if (data['senderId'] === receiverId && !initing) {
        addResponseMessage(data['message']);
        renderCustomComponent(SendTime, {
          timestamp: data['timestamp'],
          isSender: false,
        });
      }
    };

    messageRef.current.on('child_added', handleChildAdded);

    if (isCompany) {
      badgeRef.current = databaseRef
        .child('/companies_keys/')
        .child(senderId)
        .child(conversationKey)
        .child('unread');
      receiverBadgeRef.current = databaseRef
        .child('/users_keys/')
        .child(receiverId)
        .child(conversationKey)
        .child('unread');
    } else {
      badgeRef.current = databaseRef
        .child('/users_keys/')
        .child(senderId)
        .child(conversationKey)
        .child('unread');
      receiverBadgeRef.current = databaseRef
        .child('/companies_keys/')
        .child(receiverId)
        .child(conversationKey)
        .child('unread');
    }

    badgeRef.current.set(0);

    badgeRef.current.on('value', (snapshot) => {
      const data = snapshot.val();
      if (data !== 0) {
        setBadgeCount(data);
      }
    });

    fetchMessages();
    setIniting(false);

    renderCustomComponent(CloseButton, { onClose: onCloseChat });

    return () => {
      deleteMessages();
      badgeRef.current.off('value');
      messageRef.current.off('child_added', handleChildAdded);
    };
  }, [receiverId, senderId, conversationKey, isCompany, onCloseChat, initing]);

  return (
    <div className="chat-widget-container">
      <CloseButton onClose={onCloseChat} />
      <Widget
        chatId={conversationKey}
        emojis={true}
        titleAvatar={avatar}
        title="Tư vấn trực tuyến"
        subtitle={subtitle}
        handleNewUserMessage={handleSendMessage}
        senderPlaceHolder={'Nhập tin nhắn của bạn'}
        showTimeStamp={false}
      />
    </div>
  );
};

const SendTime = ({ timestamp, isSender }) => {
  return (
    <div
      className={[
        'text-muted',
        'chat-time',
        isSender ? 'sender' : 'receiver',
      ].join(' ')}
    >
      {moment(timestamp).fromNow()}
    </div>
  );
};

export default Chat;