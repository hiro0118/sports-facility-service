import { TennisCourtsPage } from "./raffle-status-page/RaffleStatusPage";
import { NotificationConfigPage } from "./notification-config-page/NotificationConfigPage";
import { PageInfo } from "../components/menu-bar/PageInfo";
import HomeIcon from '@mui/icons-material/Home';
import ConfirmationNumberIcon from '@mui/icons-material/ConfirmationNumber';
import NotificationsActiveIcon from '@mui/icons-material/NotificationsActive';
import { MainPage } from "./main/MainPage";

export const pages: PageInfo[] = [
  {
    title: "Main",
    description: "",
    path: "",
    icon: HomeIcon,
    image: "",
    element: MainPage,
  },
  {
    title: "Raffle Status",
    description: "Raffle Status Description",
    path: "raffle-status",
    icon: ConfirmationNumberIcon,
    image: "/images/raffle.png",
    element: TennisCourtsPage,
    
  },
  {
    title: "Notification Config",
    description: "Notification Config Description",
    path: "notification-config",
    icon: NotificationsActiveIcon,
    image: "/images/notification.png",
    element: NotificationConfigPage,
  }
]