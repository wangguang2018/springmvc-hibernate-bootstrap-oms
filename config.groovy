environments {

    // 开发模式（本地使用）
    dev {
        spring {
            profiles {
                active = "dev"
            }
        }
    }

    // 测试模式（测试环境使用）
    test {
        spring {
            profiles {
                active = "test"
            }
        }
    }

    // 生产模式（任我抓）
    production {
        spring {
            profiles {
                active = "production"
            }
        }
    }

}