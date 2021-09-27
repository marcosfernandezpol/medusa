import { init } from "./appFetch";
import * as userService from "./userService";
import * as stockMarketService from "./stockMarketService";

export { default as NetworkError } from "./NetworkError";

export default { init, userService, stockMarketService };
