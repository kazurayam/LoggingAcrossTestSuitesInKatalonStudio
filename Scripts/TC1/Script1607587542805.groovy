import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import my.GlobalLogger
import my.GlobalLoggerFactory

WebUI.delay(10)

String message = "ID=${GlobalVariable.ID}"
WebUI.comment(message)
GlobalLogger logger = GlobalLoggerFactory.getInstance("tmp")
logger.log(message)