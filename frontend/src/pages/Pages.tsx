import { TennisCourtsPage } from "./raffle-status-page/RaffleStatusPage";
import { NotificationConfigPage } from "./notification-config-page/NotificationConfigPage";
import { PageInfo } from "../components/menu-bar/PageInfo";
import SportsTennisIcon from '@mui/icons-material/SportsTennis';
import NotificationsActiveIcon from '@mui/icons-material/NotificationsActive';

export const pages: PageInfo[] = [
  {
    title: "Raffle Status",
    path: "raffle-status",
    icon: SportsTennisIcon,
    element: TennisCourtsPage,
  },
  {
    title: "Notification Config",
    path: "notification-config",
    icon: NotificationsActiveIcon,
    element: NotificationConfigPage,
  }
]